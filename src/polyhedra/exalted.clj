(ns polyhedra.exalted
  (:require [polyhedra.dice :as d]))

(defn pool
  "Roll a 'dice pool' of ten-sided dice."
  [num]
  (repeatedly num (partial d/d10)))

(defn successes
  "Determine how many successes the result of a ten-sided dice is in the Exalted RPG system."
  [die]
  (cond
    (= die 10) 2
    (>= die 7) 1
    :else 0))

(defn count-successes
  "Count the number of successes over all dice in a roll"
  [rolls]
  (reduce + (map successes rolls)))

(defn one?
  [die]
  (= 1 die))

(defn botch?
  "Determine if a roll is a botch, i.e., has no successes and at least one '1'."
  [rolls]
  (and (= (count-successes rolls) 0) (some one? rolls)))

(defn botch-level
  "Determine the botch 'level' of a roll, based on how many '1' results there are."
  [rolls]
  (count (filter one? rolls)))

(defn interrogate
  [rolls]
  (cond
    (botch? rolls) {::result ::botch
                    ::botch-level (botch-level rolls)}
    (< 0 (count-successes rolls)) {::result ::success
                                   ::successes (count-successes rolls)}
    :else {::result ::failure}))

(defn describe-roll
  [rolls]
  (let [i (interrogate rolls)]
    (str "Rolled: " (-> rolls seq pr-str)
         " • "
         (condp = (::result i)
           ::success (str "Successes " (count-successes rolls))
           ::failure (str "Failure")
           ::botch (str "Botch level " (botch-level rolls))))))

(defn ex
  "Exalted dice roll. Provide the number of dice to roll and get a count of successes, or a botch level"
  [num]  
  (describe-roll (pool num)))
