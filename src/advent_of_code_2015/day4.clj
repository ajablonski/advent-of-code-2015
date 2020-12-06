(ns advent-of-code-2015.day4
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.security MessageDigest)))

(def day-4-input (slurp (io/resource "day4.txt")))

(let [algorithm (MessageDigest/getInstance "MD5")]
  (defn find-first-hash-starting-with
    "Finds the number such that the MD5 of (secret + number) is prefix"
    [secret prefix]
    (first
      (filter
        #(str/starts-with?
           (format "%032x"
                   (BigInteger. 1 (.digest algorithm (.getBytes (str secret %)))))
           prefix)
        (iterate inc 1)))))

(defn main-1
  []
  (println (find-first-hash-starting-with day-4-input "00000")))

(defn main-2
  []
  (println (find-first-hash-starting-with day-4-input "000000")))
