(ns io.torsten.hexcompress-test
  (:require [midje.sweet :refer :all]
            [io.torsten.hexcompress :refer :all]))

(tabular
  (fact "compress and decompress are compatible"
    (seq (compress ?plaintext)) => (seq ?compressed)
    (decompress ?compressed) => ?plaintext)
  ?plaintext ?compressed
  "asd"      (.getBytes "asd")
  "asdf"     (byte-array [(int \a), (int \s), -33]) ; -33 == 223 == 0xdf
  "asdF"     (.getBytes "asdF")
  "1f"       (byte-array [0x1f])
  "1fA"      (byte-array [0x1f, (int \A)])
  "z1f"      (byte-array [(int \z), 0x1f])
  "00"       (byte-array [(int \0), (int \0)])
  "41"       (byte-array [(int \4), (int \1)]))

(fact "decompresses ASCII as is"
  (decompress (.getBytes "caffeebabe")) => "caffeebabe")
