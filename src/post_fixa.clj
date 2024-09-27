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

(println)

;; error handling (amb excepcions)

(defn post-fixa-excepcions [e]
    (loop [se (str/split e #"\s") s []]
        (if (empty? se)
            (if (= (count s) 1)
                (peek s)
                (try
                    (throw (Exception. "Op?!"))
                    (catch Exception e (.getMessage e))
                )
            ) 
            ;; en els operadors assumeixo que la pila no es queda buida a mitges
                (if (and (< (count s) 2) (or (= (first se) "+") (= (first se) "-") (= (first se) "*") (= (first se) "/")))
                    (try
                        (throw (Exception. "2ops"))
                        (catch Exception e (.getMessage e))
                    )
                    (cond (= (first se) "+") (recur (subvec se 1) (conj (pop (pop s)) (+ (peek (pop s)) (peek s))))
                        (= (first se) "-") (if (> 0 (- (peek (pop s)) (peek s))) 
                                                (try (throw (Exception. "neg")) 
                                                    (catch Exception e (.getMessage e)))
                                                (recur (subvec se 1) (conj (pop (pop s)) (- (peek (pop s)) (peek s)))))
                        (= (first se) "*") (recur (subvec se 1) (conj (pop (pop s)) (* (peek (pop s)) (peek s))))
                        (= (first se) "/") (cond 
                                                (zero? (peek s)) (try
                                                                        (throw (Exception. "div0"))
                                                                        (catch Exception e (.getMessage e))
                                                                )
                                                                    
                                                (not (integer? (/ (peek (pop s)) (peek s)))) (try
                                                                                                    (throw (Exception. "divE"))
                                                                                                    (catch Exception e (.getMessage e))
                                                                                            )
                                        

                                                :else (recur (subvec se 1) (conj (pop (pop s)) (/ (peek (pop s)) (peek s))))
                                            )
                        :else (if (some #{(first (first se))} '(\0 \1 \2 \3 \4 \5 \6 \7 \8 \9))
                                (recur (subvec se 1) (conj s (Integer/parseInt (first se))))
                                (try 
                                    (throw (Exception. "NaN"))
                                    (catch Exception e (.getMessage e))
                                )
                            )
                        
                            
                                
                                
                            
                    )
                )
                
            
        )
    )
)

(println "post-fixa-excepcions 22: " (post-fixa-excepcions "10 1 + 2 *"))
(println "post-fixa-excepcions 22: " (post-fixa-excepcions "2 10 1 + *"))
(println "post-fixa-excepcions 0: " (post-fixa-excepcions "3 4 + 7 11 * + 2 2 - *"))
(println "post-fixa-excepcions 1: " (post-fixa-excepcions "4 3 -"))
(println "post-fixa-excepcions 4: " (post-fixa-excepcions "12 3 /"))
(println "testing excepcions")

(println "post-fixa-excepcions neg: " (post-fixa-excepcions "3 4 -"))
(println "post-fixa-excepcions div0: " (post-fixa-excepcions "12 0 /"))
(println "post-fixa-excepcions divE: " (post-fixa-excepcions "12 5 /"))
(println "post-fixa-excepcions 2ops: " (post-fixa-excepcions "3 +"))
(println "post-fixa-excepcions NaN: " (post-fixa-excepcions "a +"))
(println "post-fixa-excepcions Op?!: " (post-fixa-excepcions "3 4"))
