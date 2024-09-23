(ns exercici_7)
(println)
;;apartat 1
(defn build-palindrome [lst]

    (loop [new_lst '() aux_lst lst]
        (if (empty? aux_lst)
            (concat new_lst lst)
            (let [new_lst2 (conj new_lst (first aux_lst))
                  aux_lst2 (pop aux_lst)]
                (recur new_lst2 aux_lst2)
            )
        )
    )
)

(println "palindrome 246: " (build-palindrome '(2 4 6)))
(println "palindrome 3: " (build-palindrome '(3)))
(println "palindrome buit: " (build-palindrome '()))

(println)

;;apartat 2
(defn remove-list [x y]
    (letfn [(remove-element [lst res e]
        (if (empty? lst)
            res 
            (let [ne (last lst)]
                (if (= ne e)
                    (recur (butlast lst) res e)
                    (recur (butlast lst) (conj res ne) e)
                )
            )
        )
    )]
    
    (if (empty? y)
        x
        (loop [x_aux x 
               y_aux y]
                (if (empty? y_aux)
                    x_aux
                    (recur (remove-element x_aux '() (first y_aux)) (pop y_aux))
                )
            
        )
    )
    )
    
)

(println "remove-list (1 5 3 5 1): " (remove-list '(1 4 5 3 4 5 1 2) '(2 4)))
(println "remove-list (1 5 3 5 1 1): " (remove-list '(1 4 5 3 4 5 1 2 1) '(2 4 0)))

(println)

;; apartat 3
(defn odds-n-evens [lst]
    (loop [aux lst even_lst '() odd_lst '()]
        (if (empty? aux)
            (list odd_lst even_lst)
            (let [ne (last aux) new_aux (butlast aux)]
                (if (even? ne)
                    (recur new_aux (conj even_lst ne) odd_lst)  ;;; falta afegir els 2 recur s
                    (recur new_aux even_lst (conj odd_lst ne))
                )
            )
        )
    )
)

(println "odds-n-even ((1 5 3 5 1 7) (4 4 2 4 2)): " (odds-n-evens '(1 4 5 3 4 5 1 2 7 4 2)))

(println)

;; apartat 4
;;  em surt la llista del revés, s'hauria de començar a sqrt de n i aixi ens estalviem calculs 
;; si resulta que era primer
(defn prime-divisors [n]
    (letfn [(prime? [primes x]
        (loop [aux_primes primes]
            (if (or (empty? aux_primes) (= x 2))
                true
                (if (zero? (mod x (first aux_primes)))
                    false
                    (recur (pop aux_primes))
                )
            )
        )
    )]
        (cond (<= n 0) "enter no estrictament positiu"
            (= n 1) "1 no té primers divisors" 
            :else (loop [y n prime-nums '() divisors '() k 2]
                    ;;(println "print debug" prime-nums divisors k y)
                    (if (= y 1) 
                        divisors
                        (if (prime? prime-nums k)
                            (if (zero? (mod y k))
                                (recur (/ y k) (conj prime-nums k) (conj divisors k) k)
                                (recur y (conj prime-nums k) divisors (inc k))
                            )
                            (recur y prime-nums divisors (inc k))
                        )
                    )
                )
        )
    )
)

(println "prime-divisor 0 --> X: " (prime-divisors 0))
(println "prime-divisor 1 --> X: " (prime-divisors 1))
(println "prime-divisor 2 --> (2): " (prime-divisors 2))
(println "prime-divisor 3 --> (3): " (prime-divisors 3))
(println "prime-divisor 12 --> (2 2 3): " (prime-divisors 12))
(println "prime-divisor 17 --> (17): " (prime-divisors 17))
(println "prime-divisor 255 --> (3 5 17): " (prime-divisors 255))
(println "prime-divisor  1001 --> (): " (prime-divisors 1001))

(println)