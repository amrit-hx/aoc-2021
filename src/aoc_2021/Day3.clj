(require '[clojure.string :as str])

(def txt "/Users/amrit/code/clojure/aoc-2021/src/aoc_2021/Day3_input.txt")

; Convert the input into a vector of integer vectors representing the bits
(def input
  (->>
   txt
   (slurp)
   (str/split-lines)
   (map #(str/split % #""))
   (map #(map read-string %))))

(def threshold (/ (count input) 2))


; You can just do a bitwise sum and see if it exceeds half the length of the list - there can only be two values, so if the number of 1s exceeds half the length then it must be more than the number of zeroes

(defn popular-at-idx
  [lst idx]
  (let [threshold (/ (count lst) 2)
        freqs
        (->>
         lst
         (map #(nth % idx))
         (reduce +))]
    ; Note >= so ties become 1s
    (if (>= freqs threshold) 1 0)))


(defn getgamma [lst]
  (if (= (count (first lst)) 0)
    nil
    (cons (popular-at-idx lst 0) (getgamma (map rest lst)))))

(def gamma (getgamma input))

(def epsilon
  (map #(- 1 %) gamma))

(defn decimalise [lst]
  (->>
   lst
   (apply str "2r")
   (read-string)))

(*
 (decimalise gamma)
 (decimalise epsilon))

(defn sieve [lst pos sieve-fn]
  (if (<= (count lst) 1)
    (first lst)
    (sieve
     (filter #(= (sieve-fn lst pos) (nth % pos)) lst)
     (inc pos)
     sieve-fn)))

(def o2 (sieve input 0 popular-at-idx))

(def unpopular-at-idx (comp  #(- 1 %) popular-at-idx))

(def co2 (sieve input 0 unpopular-at-idx))

(*
 (decimalise o2)
 (decimalise co2))
