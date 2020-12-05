(ns advent-of-code-2015.day2_test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day2 :refer :all]))

(deftest get-paper-needed-test
  (testing "Get area as rectangular prism plus smallest side"
    (is (= (get-paper-needed 2 3 4)
           58))))

(deftest get-ribbon-needed-test
  (testing "Get ribbon as smallest perimeter plus volume"
    (is (= (get-ribbon-needed 2 3 4)
           34))))

(deftest integration-test-main-1
  (testing "Integration test part 1"
    (is (= "101\n"
           (with-out-str (main-1))))))

(deftest integration-test-main-2
  (testing "Integration test part 2"
    (is (= "48\n"
           (with-out-str (main-2))))))


(run-all-tests)