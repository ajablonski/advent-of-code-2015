(ns advent-of-code-2015.day1_test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day1 :refer :all]))

(deftest get-floor-history-test
  (testing "Should get history going up"
    (is (= (reverse (get-floor-history "((("))
           '(0 1 2 3))))
  (testing "Should get history going down"
    (is (= (reverse (get-floor-history ")))"))
           '(0 -1 -2 -3))))
  (testing "Should go up and down"
    (is (= (reverse (get-floor-history "(())))(("))
           '(0 1 2 1 0 -1 -2 -1 0)))))

(deftest integration-test-main-1
  (testing "Integration test part 1"
    (is (= "3\n"
           (with-out-str (main-1))))))

(deftest integration-test-main-2
  (testing "Integration test part 2"
    (is (= "1\n"
           (with-out-str (main-2))))))

(run-all-tests)

