(ns dice-roller.core
  (:gen-class))

(defn roll-die
  [size]
  (inc (rand-int size)))

(defn roll
  [num size]
  (take num (repeatedly (partial roll-die size))))

(defn d10s
  [num]
  (roll num 10))

(defn successes
  [die]
  (cond
    (= die 10) 2
    (>= die 7) 1
    :else 0))

(defn count-successes
  [rolls]
  (reduce + (map successes rolls)))

(defn one?
  [die]
  (= 1 die))

(defn botch?
  [rolls]
  (and (= (count-successes rolls) 0) (some one? rolls)))

(defn botch-level
  [rolls]
  (count (filter one? rolls)))

(defn exalted-roll
  [num]  
  (let [rolls (d10s num)]
    (str "Rolled: " (pr-str rolls)
         " • "
         (if (botch? rolls)
           (str "Botch level " (botch-level rolls))
           (str "Successes " (count-successes rolls))))
    ))

(def ex exalted-roll)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
