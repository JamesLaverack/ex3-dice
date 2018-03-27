(ns polyhedra.dice)

(defn die [size]
  "Roll a single die of a given size"
  (inc (rand-int size)))

;; Functions for common die types
(defn d100 [] (die 100))
(defn d20 [] (die 20))
(defn d12 [] (die 12))
(defn d10 [] (die 10))
(defn d8 [] (die 8))
(defn d6 [] (die 6))
(defn d4 [] (die 4))
(defn d3 [] (die 3))
(defn d2 [] (die 2))
