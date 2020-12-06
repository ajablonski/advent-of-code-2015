(ns advent-of-code-2015.day5-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day5 :refer :all]))

(deftest nice-1?-test
  (testing "Nice strings"
    (is (true? (nice-1? "aaa")))
    (is (true? (nice-1? "ugknbfddgicrmopn"))))
  (testing "Should not count as nice when missing double letter"
    (is (false? (nice-1? "jchzalrnumimnmhp"))))
  (testing "Should not count as nice when contains forbidden strings"
    (is (false? (nice-1? "haegwjzuvuyypuab")))
    (is (false? (nice-1? "haegwjzuvuyypucd")))
    (is (false? (nice-1? "haegwjzuvuyypupq")))
    (is (false? (nice-1? "haegwjzuvuyypuxy"))))
  (testing "Should not count as nice when has fewer than 3 vowels"
    (is
      (false? (nice-1? "dvsazwmarrgswjxmb")))))

(deftest nice-2?-test
  (testing "Nice strings"
    (is (true? (nice-2? "qjhvhtzxzqqjkmpb")))
    (is (true? (nice-2? "xxyxx"))))
  (testing "Not nice strings"
    (is (false? (nice-2? "uurcxstgmygtbstg")))
    (is (false? (nice-2? "ieodomkazucvgmuy"))))
  )

(deftest integration-test-main-1
  (testing "Integration test part 1"
    (is (= "2\n"
           (with-out-str (main-1))))))

(deftest integration-test-main-2
  (testing "Integration test part 2"
    (is (= "3\n"
           (with-out-str (main-2))))))
