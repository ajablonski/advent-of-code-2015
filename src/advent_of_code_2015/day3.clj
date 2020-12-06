(ns advent-of-code-2015.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(def day-3-input (io/resource "day3.txt"))

(defn move
  [position movement]
  (cond
    (= movement \^) (update position :y inc)
    (= movement \<) (update position :x dec)
    (= movement \v) (update position :y dec)
    (= movement \>) (update position :x inc)
    :else position))


(let [starting-position {:x 0 :y 0}]
  (defn get-positions
    [movements]
    (first
      (reduce
        (fn [[positions current-position] movement]
          (let [next-position (move current-position movement)]
            (list (conj positions next-position) next-position)))
        (list #{starting-position} starting-position)
        movements))))

(defn main-1
  []
  (println
    (count (get-positions (slurp day-3-input)))))

(defn main-2
  []
  (let [movements (slurp day-3-input)
        idx-keep-p-fn (fn [p-fn] (fn [idx item] (if (p-fn idx) item)))
        real-santa-movements (keep-indexed (idx-keep-p-fn odd?) movements)
        robo-santa-movements (keep-indexed (idx-keep-p-fn even?) movements)]
    (println
      (count
        (set/union (get-positions real-santa-movements)
                   (get-positions robo-santa-movements))))))
