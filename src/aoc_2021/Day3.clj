(require '[clojure.string :as str])

(def txt "/Users/amrit/code/clojure/aoc-2021/src/aoc_2021/Day3_input.txt")

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
   ; transpose the lists so you can sum them
   (apply map vector)
   (map #(reduce + %))
   ; And check if above the threshold
   (map #(> % threshold))))

; Convert to stream of 1s and 0s
(def gamma
  (->>
   freqs
   (map #(if % 1 0))))

(def epsilon
  (map #(- 1 %) gamma))

(defn decimalise [lst]
  (->>
   lst
   (apply str)
   (str "2r")
   (read-string)))

(*
 (decimalise gamma)
 (decimalise epsilon))

; Part 2
; You need to filter the list by matching the nth bit of each member to the nth bit of gamma
(defn sieve [mask lst]
  )