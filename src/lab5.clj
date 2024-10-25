(ns lab5)

;; exercici 1
(println "Exercici 1:")
(defn misteri [n]
  (let [secret 4
        n (+ n 2)]
    (fn [mult]
      (* secret (* mult n)))))

(defn misteri3 [param]
  (fn [bonus]
    (+ (param 6) bonus)))

(let [h (misteri 3)  ;; dona #(* 20 %)
      j (misteri3 h)
      result (j 2)]
  (println result))

(println)

;;(println "misteri 3:" ((misteri 3) 1) ((misteri 3) 2) ((misteri 3) 3))

;; Exercici 2
(defn punt [x y]
  (fn
    ([kw] 
     (cond (= kw :crt) (list x y)
           (= kw :plr) (list (Math/sqrt (+ (* x x) (* y y))) (/ (* (Math/atan2 y x) 180) Math/PI))
           
           )) 
    ([kw p]
     (cond (= kw :dst)
       ((comp Math/sqrt (partial reduce +) (partial map #(* % %)) (partial map -)) [x y] (p :crt))
       )) 
     )

     )
  
 

(println "Exercici 2:")
(println "(2 0) -->: " ((punt 2 0) :crt))
(println "(2.0 0.0) -->: " ((punt 2 0) :plr))
(println "(2 2) -->: " ((punt 2 2) :crt))
(println "(2.8284271247461903 45.0) -->: " ((punt 2 2) :plr))

(println)
(println "Exercici 2bis:")
(println "Exercici 2--> 2.0: " ((punt 2 2) :dst (punt 2 0)))
(println "Exercici 2--> 0.0: " ((punt 2 0) :dst (punt 2 0)))

(defn mes-propera [p v]
  ((comp second (partial reduce #(if (> (first %1) (first %2)) %2 %1)) (partial map (fn [np] [(p :dst np) (np :crt)]))) v)
  )
(println "mes-propera (2 1) -->: " (mes-propera (punt 2 0) (list (punt 1 1) (punt 2 1) (punt 3 2))))
(println)

;; Exercici 3
(defn my-partial [op val]
  (fn [& x] 
    (apply op (conj x val))
    
    )
  )

(println "Exercici 3:")
(println "my-partial 8 -->:" ((my-partial * 2) 4))
(println "my-partial 24 -->:" ((my-partial * 2) 4 3))
