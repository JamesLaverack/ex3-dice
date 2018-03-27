(ns polyhedra.dice-test
  (:require [clojure.test :refer :all]
            [polyhedra.dice :refer :all]))

(deftest die-test
  ;; Redef rand-int to return the biggest thing it could possibly return, which is the value minus one.
  (with-redefs [clojure.core/rand-int (fn [size] (dec size))]
    (is (= 1337 (die 1337)))
    (is (= 100 (d100)))
    (is (= 20 (d20)))
    (is (= 12 (d12)))
    (is (= 10 (d10)))
    (is (= 8 (d8)))
    (is (= 6 (d6)))
    (is (= 4 (d4)))
    (is (= 3 (d3)))
    (is (= 2 (d2)))))


