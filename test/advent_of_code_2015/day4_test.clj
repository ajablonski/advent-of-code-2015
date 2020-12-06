(ns advent-of-code-2015.day4-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day4 :refer :all]))

(deftest integration-test-main-1
  (testing "Integration test part 1"
    (is (= "609043\n"
           (with-out-str (main-1))))))

(deftest integration-test-main-2
  (testing "Integration test part 2"
    (is (= "6742839\n"
           (with-out-str (main-2))))))
