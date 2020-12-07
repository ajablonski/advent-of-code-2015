(ns advent-of-code-2015.day6
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn- change-light-state
  [state-change-fn grid [start-row start-col] [end-row end-col]]
  (vec
    (map-indexed
      (fn [row-num row]
        (vec
          (map-indexed
            (fn [col-num entry]
              (if (and
                    (<= start-row row-num end-row)
                    (<= start-col col-num end-col))
                (state-change-fn entry)
                entry))
            row)
          ))
      grid)))

(defn turn-on
  [grid start-pos end-pos]
  (change-light-state (fn [entry]
                        (cond (keyword? entry) :on
                              (integer? entry) (inc entry)))
                      grid start-pos end-pos))

(defn turn-off
  [grid start-pos end-pos]
  (change-light-state (fn [entry]
                        (cond (keyword? entry) :off
                              (integer? entry) (max 0 (dec entry))))
                      grid start-pos end-pos))

(defn toggle
  [grid start-pos end-pos]
  (change-light-state (fn [entry]
                        (cond (keyword? entry) (if (= entry :on) :off :on)
                              (integer? entry) (+ entry 2))) grid start-pos end-pos))

(defn init-grid
  ([& {:keys [initial-state size] :or {initial-state :off size 1000}}]
   (vec (repeat size (vec (repeat size initial-state))))))

(defn light-count
  [grid state]
  (reduce + (map (fn [row] (count (filter #(= % state) row))) grid)))

(defn light-brightness
  [grid]
  (reduce (fn [sum row] (+ sum (reduce + row))) 0 grid))

(defn parse-instruction
  [instruction]
  (let [matcher (re-matcher #"(turn on|turn off|toggle) ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)" instruction)
        [match instruction start-row-str start-col-str end-row-str end-col-str] (re-find matcher)
        change-fn (cond (= instruction "turn on") turn-on
                        (= instruction "turn off") turn-off
                        (= instruction "toggle") toggle
                        :else identity)]
    (if (some? match)
      (fn [grid]
        (change-fn
          grid
          [(Integer/parseInt start-row-str) (Integer/parseInt start-col-str)]
          [(Integer/parseInt end-row-str) (Integer/parseInt end-col-str)]))
      identity)))


(def day-6-input (slurp (io/resource "day6.txt")))

(defn- do-main
  [sum-fn initial-state]
  (println
    (sum-fn
      (reduce
        (fn [grid instruction-str]
          ((parse-instruction instruction-str) grid))
        (init-grid :size 1000 :initial-state initial-state)
        (str/split-lines day-6-input)))))

(defn main-1
  []
  (do-main
    (fn [grid] (light-count grid :on))
    :off))

(defn main-2
  []
  (do-main
    light-brightness
    0))
