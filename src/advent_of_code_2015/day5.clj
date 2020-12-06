(ns advent-of-code-2015.day5
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def day-5-input (io/resource "day5.txt"))

(defn nice-1?
  [string]
  (and
    (>= (count (re-seq #"[aeiou]" string)) 3)
    (not
      (or
        (str/includes? string "ab")
        (str/includes? string "cd")
        (str/includes? string "pq")
        (str/includes? string "xy")))
    (some? (re-matches #".*(.)\1.*" string))
    ))

(defn nice-2?
  [string]
  (and
    (some? (re-matches #".*([a-z]{2}).*\1.*" string))
    (some? (re-matches #".*(.).\1.*" string))
    ))

(defn main-1
  []
  (println
    (count (filter nice-1? (str/split-lines (slurp day-5-input))))))

(defn main-2
  []
  (println
    (count (filter nice-2? (str/split-lines (slurp day-5-input))))))
