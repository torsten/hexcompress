(ns io.torsten.hexcompress)

(def hex-bytes
  (set (map #(->> % (format "%x") .getBytes first byte) (range 16))))

(def printable-ascii
  (conj (set (map byte (range (int \!) 127))) (byte 0)))

(defn hex-encode
  "Takes to bytes and hex-encodes them into one
   ['f', 'f'] => 0xff."
  [a b]
  (Integer/parseInt (String. (byte-array [a b])) 16))

(defn drop-last-vec
  "Like drop-last but efficiently for vectors."
  [v]
  (let [c (count v)]
    (if (= c 0)
      v
      (subvec v 0 (- c 1)))))

(defn compress
  "Compress a string into bytes."
  [string]
  (byte-array
    (first
      (reduce
        (fn [[result prev] next]
          (if (and (hex-bytes prev)
                   (hex-bytes next)
                   (not (printable-ascii (hex-encode prev next))))
            [(conj (drop-last-vec result) (hex-encode prev next)) nil]
            [(conj result next) next]))
        [[] nil]
        (.getBytes string)))))

(defn decompress
  "Decompress bytes into a new string."
  [bytes]
  (new String
    (byte-array
      (mapcat
        (fn [byte]
          (if (not (printable-ascii byte))
            (map int (format "%02x" byte))
            [byte]))
        bytes))))
