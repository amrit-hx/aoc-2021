(require '[clojure.string :as str])

(def txt "/Users/amrit/code/clojure/aoc-2021/src/aoc_2021/Day4_input.txt")

; Split with args reversed so you can use it in threading
(defn split-rev [a b] (str/split b a))

(def input
  (->>
   (slurp txt)
   (split-rev #"\n\n")))


(def nums
  (->>
   (first input)
   ; This reverses the order of split so you can thread it. Feels hacky?
   (split-rev #",")))

(def boards
  (->>
   (rest input)
   ; TODO: This is losing the partitioning between the boards. You need to change the parsing
   (map #(str/split % #"\n"))
   (map #(map read-string %))))
