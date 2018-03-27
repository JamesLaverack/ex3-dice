(ns dice-roller.exalted
  (:require [dice-roller.dice :as dice]))

(defn pool
  "Roll a 'dice pool' of ten-sided dice."
  [num]
  (repeatedly num (partial dice/d10)))

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

(defn ex
  "Exalted dice roll. Provide the number of dice to roll and get a count of successes, or a botch level"
  [num]  
  (let [rolls (pool num)]
    (str "Rolled: " (pr-str rolls)
         " • "
         (if (botch? rolls)
           (str "Botch level " (botch-level rolls))
           (str "Successes " (count-successes rolls))))))
