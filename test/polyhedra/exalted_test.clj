(ns polyhedra.exalted-test
  (:require [clojure.test :refer :all]
            [polyhedra.exalted :as ex]))

(deftest successes-test
  (testing "That a seven and above is a success"
    (is (= 1 (ex/successes 7)))
    (is (= 1 (ex/successes 8)))
    (is (= 1 (ex/successes 9)))) 
  (testing "The 'Double Tens' rule"
    (is (= 2 (ex/successes 10))))
  (testing "Successes are summed over a roll"
    (is (= 4 (ex/count-successes [2 7 8 10 1])))))

(deftest botch-test
  (testing "That a botch is correctly detected"
    (is (ex/botch? [1]))
    (is (ex/botch? [1 3 1]))
    (is (not (ex/botch? [])))
    (is (not (ex/botch? [7])))
    (is (not (ex/botch? [10]))))
  (testing "That the level of a botch is detected"
    (is (= 1 (ex/botch-level [1 3 4])))
    (is (= 4 (ex/botch-level [1 6 1 1 1])))
    (is (= 0 (ex/botch-level [0])))))

(deftest interrogate-test
  (testing "That botches are detected"
    (is (= {:polyhedra.exalted/result :polyhedra.exalted/botch
            :polyhedra.exalted/botch-level 3}
           (ex/interrogate [3 1 1 1]))))
  (testing "That failures are detected"
    (is (= {:polyhedra.exalted/result :polyhedra.exalted/failure}
           (ex/interrogate [3 3 2 6 4]))))
  (testing "That successes are detected"
    (is (= {:polyhedra.exalted/result :polyhedra.exalted/success
            :polyhedra.exalted/successes 4}
           (ex/interrogate [5 7 3 8 10])))))
