(ns advent-of-code-2015.day3-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day3 :refer :all]))

(deftest move-test
  (testing "Move left" (is (= (move {:x 0 :y 0} \>) {:x 1 :y 0})))
  (testing "Move right" (is (= (move {:x 0 :y 0} \<) {:x -1 :y 0})))
  (testing "Move up" (is (= (move {:x 0 :y 0} \^) {:x 0 :y 1})))
  (testing "Move down" (is (= (move {:x 0 :y 0} \v) {:x 0 :y -1}))))


(deftest get-positions-test
  (testing "No movement"
    (is
      (= (get-positions "")
         #{{:x 0 :y 0}})))
  (testing "Movements in all directions"
    (is
      (= (get-positions ">^<<v")
         #{
           {:x 0 :y 0}
           {:x 1 :y 0}
           {:x 1 :y 1}
           {:x 0 :y 1}
           {:x -1 :y 1}
           {:x -1 :y 0}
           })))
  (testing "Do not repeat positions"
    (is
      (= (get-positions ">^<v")
         #{
           {:x 0 :y 0}
           {:x 1 :y 0}
           {:x 1 :y 1}
           {:x 0 :y 1}
           }))))

(deftest integration-test-main-1
  (testing "Integration test part 1"
    (is (= "4\n"
           (with-out-str (main-1))))))

(deftest integration-test-main-2
  (testing "Integration test part 2"
    (is (= "3\n"
           (with-out-str (main-2))))))

(run-all-tests)
