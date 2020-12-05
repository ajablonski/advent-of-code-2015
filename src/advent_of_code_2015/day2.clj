(ns advent-of-code-2015.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def day-2-input
  (str/split-lines
    (slurp
      (io/resource "day2.txt"))))

(defn parse-dims
  [dim-string]
  (map #(Integer/parseInt %) (str/split dim-string #"x")))

(defn get-paper-needed
  [l w h]
  (let [side-1-area (* l w)
        side-2-area (* l h)
        side-3-area (* w h)
        smallest-side-area (min side-1-area side-2-area side-3-area)]
    (+ (* 2 side-1-area) (* 2 side-2-area) (* 2 side-3-area) smallest-side-area)))

(defn get-ribbon-needed
  [l w h]
  (let [smallest-perimeter (min (+ w w h h) (+ l l h h) (+ l l w w))
        volume (* l w h)]
    (+ volume smallest-perimeter)))

(defn main-1
  []
  (println
    (reduce #(+ %1 (apply get-paper-needed (parse-dims %2))) 0 day-2-input)))

(defn main-2
  []
  (println
    (reduce #(+ %1 (apply get-ribbon-needed (parse-dims %2))) 0 day-2-input)))
