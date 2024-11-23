(ns lab9)
;; expr ::= (:val int) | (:add expr expr) | (:sub expr expr) | (:mul expr expr) | (:div expr expr)
;;2 
(defn avalua [lst]
  (let [op (first lst)]
    (if (= :val op) (second lst)
        (let [L (avalua (second lst))
              R (avalua (last lst))]
          (cond (string? L) L
                (string? R) R
                :else (case op
                        :add (+ L R)
                        :sub (- L R)
                        :mul (* L R)
                        :div (if (zero? R) (try
                                           (throw (Exception. "div0"))
                                           (catch Exception e (.getMessage e)))
                                 (/ L R))))
          
          )
        
        )
    )
  
  )

(println "avalua")
(println (avalua '(:add (:val 3) (:div (:val 4) (:val 2)))))
(println (avalua '(:add (:val 2) (:val 3))))
(println (avalua '(:mul (:add (:val 2) (:val 3)) (:sub (:val 2) (:val 3)))))
(println (avalua '(:div (:val 2) (:val 0))))
(println (avalua '(:add (:div (:val 4) (:val 0)) (:val 3))))
(println)
;; excepció que hem de posar
(comment 
  
  
  )