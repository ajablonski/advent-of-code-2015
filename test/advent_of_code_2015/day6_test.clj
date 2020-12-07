(ns advent-of-code-2015.day6-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day6 :refer :all]))



(deftest light-count-test
  (testing
    "Counts lights that are off"
    (is (= 1000000
           (light-count (init-grid) :off))))
  (testing
    "Counts lights that are on"
    (is (= 0
           (light-count (init-grid) :on))))
  )

(deftest light-brightness-test
  (testing
    "Should sum up individual brightnesses"
    (is (= 6
          (light-brightness [[0 1] [2 3]])))))

(deftest turn-on-test
  (testing
    "Turns on single light"
    (let [new-grid (turn-on (init-grid :size 4) [0 0] [0 0])]
      (is (= new-grid
             [[:on :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "Leave on already-on light"
    (let [new-grid (turn-on
                     (turn-on (init-grid :size 4) [0 0] [0 0])
                     [0 0] [0 0])]
      (is (= new-grid
             [[:on :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "Turns on partial column of lights"
    (let [new-grid (turn-on (init-grid :size 4) [0 0] [2 0])]
      (is (= new-grid
             [[:on :off :off :off]
              [:on :off :off :off]
              [:on :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "Turns on partial row of lights"
    (let [new-grid (turn-on (init-grid :size 4) [0 0] [0 2])]
      (is (= new-grid
             [[:on :on :on :off]
              [:off :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "Turns on block of lights"
    (let [new-grid (turn-on (init-grid :size 4) [0 0] [2 2])]
      (is (= new-grid
             [[:on :on :on :off]
              [:on :on :on :off]
              [:on :on :on :off]
              [:off :off :off :off]]))))
  (testing
    "Works with integers"
    (let [new-grid
          (turn-on
            (turn-on
              (init-grid :size 4 :initial-state 0)
              [0 0] [2 2])
            [0 0] [1 1])]
      (is (= new-grid
             [[2 2 1 0]
              [2 2 1 0]
              [1 1 1 0]
              [0 0 0 0]]))))
  )

(deftest toggle-test
  (testing
    "toggle single light from off to on"
    (let [new-grid (toggle (init-grid :size 4) [0 0] [0 0])]
      (is (= new-grid
             [[:on :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "toggle single light from on to off"
    (let [new-grid (toggle (init-grid :size 4 :initial-state :on) [0 0] [0 0])]
      (is (= new-grid
             [[:off :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]]))))
  (testing
    "toggling twice should restore original state"
    (let [initial-grid (init-grid :size 4 :initial-state :on)
          new-grid (toggle initial-grid [0 0] [0 0])
          final-grid (toggle new-grid [0 0] [0 0])]
      (is (= initial-grid
             final-grid))))
  (testing
    "Toggle partial column of lights"
    (let [new-grid (toggle (init-grid :size 4) [0 0] [2 0])]
      (is (= new-grid
             [[:on :off :off :off]
              [:on :off :off :off]
              [:on :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "Toggle partial row of lights"
    (let [new-grid (toggle (init-grid :size 4) [0 0] [0 2])]
      (is (= new-grid
             [[:on :on :on :off]
              [:off :off :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  (testing
    "Toggle block of lights"
    (let [new-grid (toggle (init-grid :size 4) [0 0] [2 2])]
      (is (= new-grid
             [[:on :on :on :off]
              [:on :on :on :off]
              [:on :on :on :off]
              [:off :off :off :off]]))))
  (testing
    "Works with integers"
    (let [new-grid
          (toggle
            (toggle
              (init-grid :size 4 :initial-state 0)
              [0 0] [2 2])
            [0 0] [1 1])]
      (is (= new-grid
             [[4 4 2 0]
              [4 4 2 0]
              [2 2 2 0]
              [0 0 0 0]])))))

(deftest turn-off-test
  (testing
    "Turns off single light"
    (let [new-grid (turn-off (init-grid :size 4 :initial-state :on) [0 0] [0 0])]
      (is (= new-grid
             [[:off :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]]))))
  (testing
    "Leave off already-on light"
    (let [new-grid (turn-off (turn-off (init-grid :size 4 :initial-state :on) [0 0] [0 0]) [0 0] [0 0])]
      (is (= new-grid
             [[:off :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]]))))
  (testing
    "Turns off partial column of lights"
    (let [new-grid (turn-off (init-grid :size 4 :initial-state :on) [0 0] [2 0])]
      (is (= new-grid
             [[:off :on :on :on]
              [:off :on :on :on]
              [:off :on :on :on]
              [:on :on :on :on]]))))
  (testing
    "Turns off partial row of lights"
    (let [new-grid (turn-off (init-grid :size 4 :initial-state :on) [0 0] [0 2])]
      (is (= new-grid
             [[:off :off :off :on]
              [:on :on :on :on]
              [:on :on :on :on]
              [:on :on :on :on]]))))
  (testing
    "Turns off block of lights"
    (let [new-grid (turn-off (init-grid :size 4 :initial-state :on) [0 0] [2 2])]
      (is (= new-grid
             [[:off :off :off :on]
              [:off :off :off :on]
              [:off :off :off :on]
              [:on :on :on :on]]))))
  (testing
    "Works with integers"
    (let [new-grid
          (turn-off
            (turn-off
              (init-grid :size 4 :initial-state 2)
              [0 0] [2 2])
            [0 0] [1 1])]
      (is (= new-grid
             [[0 0 1 2]
              [0 0 1 2]
              [1 1 1 2]
              [2 2 2 2]])))))

(deftest parse-instruction-test
  (testing "Should parse turn on instruction"
    (let [next-step-fn (parse-instruction "turn on 0,0 through 1,1\n")
          grid (init-grid :size 4 :initial-state :off)]
      (is (= (next-step-fn grid)
             [[:on :on :off :off]
              [:on :on :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  (testing "Should parse turn off instruction"
    (let [next-step-fn (parse-instruction "turn off 0,0 through 1,1\n")
          grid (init-grid :size 4 :initial-state :on)]
      (is (= (next-step-fn grid)
             [[:off :off :on :on]
              [:off :off :on :on]
              [:on :on :on :on]
              [:on :on :on :on]]))))
  (testing "Should parse toggle instruction"
    (let [next-step-fn (parse-instruction "toggle 0,0 through 1,1\n")
          grid [[:on :off :off :off]
                [:on :off :off :off]
                [:off :off :off :off]
                [:off :off :off :off]]]
      (is (= (next-step-fn grid)
             [[:off :on :off :off]
              [:off :on :off :off]
              [:off :off :off :off]
              [:off :off :off :off]]))))
  )

(deftest integration-test-main-1
  (testing "Integration test part 1"
    (is (= "999995\n"
           (with-out-str (main-1))))))

(deftest integration-test-main-2
  (testing "Integration test part 2"
    (is (= "1000002\n"
           (with-out-str (main-2))))))
