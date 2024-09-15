(ns exercici_7)

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
;;(defn remove-list)

;;()

(println)

;; apartat 3
;;(defn odds-n-even)

;;()

(println)

;; apartat 4
;(defn prime-divisors)

;;()

(println)