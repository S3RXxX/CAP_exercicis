(ns post_fixa)

;; expressió escrita en notació postfixa amb només nombres naturals i operadors de suma, resta, producte i divisió

;; per split de strings
(require '[clojure.string :as str])
;; (str/split my-string #"\s")


(defn post-fixa [e]
    (loop [se (str/split e #"\s") s []]
        (if (empty? se)
            (peek s) ;; assumeixo que nomes queda 1 pq esta be la expressio
            ;; en els operadors assumeixo que la pila no es queda buida a mitges
                (cond (= (first se) "+") (recur (subvec se 1) (conj (pop (pop s)) (+ (peek (pop s)) (peek s))))
                    (= (first se) "-") (recur (subvec se 1) (conj (pop (pop s)) (- (peek (pop s)) (peek s))))
                    (= (first se) "*") (recur (subvec se 1) (conj (pop (pop s)) (* (peek (pop s)) (peek s))))
                    (= (first se) "/") (recur (subvec se 1) (conj (pop (pop s)) (/ (peek (pop s)) (peek s))))
                    :else (recur (subvec se 1) (conj s (Integer/parseInt (first se)))) ;; assumeixo que numeros
                )
            
        )
    )
)


(println "post-fixa 22: " (post-fixa "10 1 + 2 *"))
(println "post-fixa 22: " (post-fixa "2 10 1 + *"))
(println "post-fixa 0: " (post-fixa "3 4 + 7 11 * + 2 2 - *"))
(println "post-fixa 1: " (post-fixa "4 3 -"))
(println "post-fixa 4: " (post-fixa "12 3 /"))

;; error handling (amb excepcions)



