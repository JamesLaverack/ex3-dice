(defproject dice-roller "0.1.0-SNAPSHOT"
  :description "Exalted 3rd Edition Dice Roller"
  :url "http://github.com/JamesLaverack/ex3-dice"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot dice-roller.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
