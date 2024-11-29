(ns llistesInfinites)

;; 1
(def ones (repeat 1N))

(println "ones")
(println (take 8 ones))
(println)

;; 2
(def naturals (iterate inc 0N))

(println "naturals")
(println (take 8 naturals))
(println)

;; 3
(def enters (cons 0N (iterate #(if (< % 0) (inc (- %)) (- %)) 1N)))

(println "enters")
(println (take 8 enters))
(println)

;; 4
(def powers-of-2 (lazy-seq (cons 1N (map #(* % 2) powers-of-2))))

(println "powers-of-2")
(println (take 8 powers-of-2))
(println)

;; 5
;(defn triangle [n] (/ (* n (+ n 1)) 2)) ;; nombres triangulars
;(def triangulars (cons 0N (map triangle (iterate inc 1N))))
(def triangulars (lazy-seq (cons 0N (map + triangulars (iterate inc 1N)))))
(println "triangulars")
(println (take 8 triangulars))
(println)

;; 6
(def factorials (lazy-seq (cons 1N (map * factorials (iterate inc 2N)))))

(println "factorials")
(println (take 8 factorials))
(println)

;; 7
(def fibs (cons 0N (lazy-seq (cons 1N (map + fibs (rest fibs))))))

(println "fibs")
(println (take 8 fibs))
(println)

;; 8
(defn sieve [s]
  (cons (first s)
         (lazy-seq (sieve
                    (filter
                     #(not= 0 (mod % (first s)))
                     (rest s))))))
(def primers (sieve (iterate inc 2N)))

(println "primers")
(println (take 8 primers))
(println)


;; 9
(defn generate-hammings 
  ([] (generate-hammings (sorted-set 1N)))
  ([ss] (let [n (first ss)
              s (disj ss n)]
          (cons n (lazy-seq (generate-hammings (conj s (* n 2N) (* n 3N) (* n 5N)))))
          )
   )
  )
(def hammings (generate-hammings))
(println "hammings")
(println (take 8 hammings))
(println)
