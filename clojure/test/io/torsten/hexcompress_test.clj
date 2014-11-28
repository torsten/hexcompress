(ns io.torsten.hexcompress-test
  (:require [midje.sweet :refer :all]
            [io.torsten.hexcompress :refer :all]))

(facts "about compress"
  (seq (compress "asd")) => (seq (.getBytes "asd"))
  (seq (compress "asdf")) => [(int \a), (int \s), -33] ; -33 == 223 == 0xdf
  (seq (compress "asdF")) => (seq (.getBytes "asdF"))
  (seq (compress "1f")) => [0x1f]
  (seq (compress "1fA")) => [0x1f, (int \A)]
  (seq (compress "z1f")) => [(int \z), 0x1f]
  (seq (compress "00")) => [(int \0), (int \0)]
  (seq (compress "41")) => [(int \4), (int \1)])
