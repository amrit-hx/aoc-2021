(require '[clojure.string :as str])
(require '[clojure.math.numeric-tower :as math :refer [expt]])

(def txt "/Users/amrit/code/clojure/AoC_2021/Day3_input.txt")

; You can just do a bitwise sum and see if it exceeds half the length of the list - there can only be two values, so if the number of 1s exceeds half the length then it must be more than the number of zeroes

; So - convert the input into a vector of integer vectors representing the bits
(def input
  (->>
   txt
   (slurp)
   (str/split-lines)
   (map #(str/split % #""))
   (map #(map read-string %))))

(def threshold (/ (count input) 2))

; Do a "bitwise" sum
(def freqs
  (->>
   input
   (apply map vector)
   (map #(reduce + 0 %))
   ; And check if above the threshold
   (map #(> % threshold))))

; Convert to decimal