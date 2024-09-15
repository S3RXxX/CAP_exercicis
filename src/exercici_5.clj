(ns exercici_5)

;; apartat 1
(def abs-value #((if (< % 0) - +) %))
(println "abs-value -666:" (abs-value -666))
(println "abs-value 5:" (abs-value 5))

(println)

;; apartat 2
;; vanila recursion
(defn power1 
    ([x p] (power1 x p 1)) 
    ([x p acum] 
        (if (< p 1) 
            acum 
            (power1 x (dec p) (* x acum)))
    )
)
(println "power1 2 0:" (power1 2 0))
(println "power1 2 1:" (power1 2 1))
(println "power1 2 3:" (power1 2 3))
(println "power1 2 4:" (power1 2 4))

(println)

;; using  recur
(defn power12 
    ([x p] (power12 x p 1)) 
    ([x p acum] 
        (if (< p 1) 
            acum 
            (let [d (dec p)]
                (recur x d (*' acum x))
            )
        )
    )
)

(println "power12 2 0:" (power12 2 0))
(println "power12 2 1:" (power12 2 1))
(println "power12 2 3:" (power12 2 3))
(println "power12 2 4:" (power12 2 4))

(println)

;; apartat 3
(defn power2 [x p]
    (loop [i p
           acum 1]
        (if (< i 1)
            acum
            (let [d (dec i)]
                (recur d (*' acum x))
            )
        )
    )
)

(println "power2 2 0:" (power2 2 0))
(println "power2 2 1:" (power2 2 1))
(println "power2 2 3:" (power2 2 3))
(println "power2 2 4:" (power2 2 4))

(println)

;; apartat 4
;; força bruta mira tots els nums fins arrel de x
(defn prime? [x] 
    (if (<= x 1)
        false
        (loop [i 2]
            (if (> (* i i) x)
                true
                (if (zero? (mod x i))
                    false
                    (let [j (inc i)]
                        (recur j))
                )
            )
        )
    )
)

(println "prime? 1" (prime? 1))
(println "prime? 2" (prime? 2))
(println "prime? 3" (prime? 3))
(println "prime? 4" (prime? 4))
(println "prime? 17" (prime? 17))
(println "prime? 21" (prime? 21))