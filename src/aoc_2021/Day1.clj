(require '[clojure.string :as str])

(def txt "/Users/amrit/code/clojure/aoc-2021/src/aoc_2021/Day1_input.txt")

(def nums
  (->>
   txt
   (slurp)
   (str/split-lines)
   (map read-string)))

(defn count_incrs
  [list]
  (if (<= (count list) 1)
    0
    (+
     (if (> (second list) (first list)) 1 0)
     (count_incrs (rest list)))))

; Answer to first part
(count_incrs nums)

(def windowed-nums
  (->>
   nums
   (partition 3 1)
   (map #(apply + %))))

; Answer to second part
(count_incrs windowed-nums)

