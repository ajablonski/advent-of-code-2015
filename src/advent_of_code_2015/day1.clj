(ns advent-of-code-2015.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def day-1-input (io/resource "day1.txt"))


(defn get-floor-history
  [elevator-string]
  (reduce
             (fn [floors up-or-down]
               (let [next-floor (+ (first floors) (if (= up-or-down \() 1 -1))]
                 (cons next-floor floors)
                 ))
             '(0)
             elevator-string))

(defn get-end-floor
  [elevator-string]
  (first (get-floor-history elevator-string)))


(defn get-first-basement
  [elevator-string]
  (count (take-while #(>= % 0) (reverse (get-floor-history elevator-string)))))

(defn main-1 []
  (println (get-end-floor (slurp day-1-input))))

(defn main-2 []
  (println (get-first-basement (slurp day-1-input))))

