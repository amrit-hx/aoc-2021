(require '[clojure.string :as str])

(def txt "/Users/amrit/code/clojure/AoC_2021/Day2_input.txt")

(defn parse-to-instruction [s]
  (let [[direction distance] (str/split s #" ")]
    [direction (read-string distance)]))

(def input
  (->>
   txt
   (slurp)
   (str/split-lines)
   (map parse-to-instruction)))

(defn move
  [x y [[direction distance] & rest-of-instrs :as all]]
  (if (empty? all)
    (* x y)
    (case direction
      "forward" (recur (+ x distance) y rest-of-instrs)
      "down" (recur x (+ y distance) rest-of-instrs)
      "up" (recur x (- y distance) rest-of-instrs))))

(move 0 0 input)

(defn move-2
  [x y aim [[direction distance] & rest-of-instrs :as all]]
  (if (empty? all)
    (* x y)
    (case direction
      "forward" (recur (+ x distance) (+ y (* distance aim)) aim rest-of-instrs)
      "down" (recur x y (+ aim distance) rest-of-instrs)
      "up" (recur x y (- aim distance) rest-of-instrs))))

(move-2 0 0 0 input)