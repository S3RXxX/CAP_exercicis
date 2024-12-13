(ns macros)

;; Lab macros

;; unless
(defmacro unless [test & body] 
  (list 'if `(not ~test) (cons 'do body))
  )

(println "unless")
(println (unless false (println "Aquest missatge es mostra perquè la condició és falsa.")))
(println (unless true (println "Aquest missatge no es mostra perquè la condició és certa.")))
(println)

;; bulce foreach
(defmacro foreach [[simbol iterable :as params] & cos]
  (cond (not (vector? params))
        (throw (Error. "El 1r argument ha de ser un vector amb el format adequat"))
        (not= 2 (count params))
        (throw (Error. "Calen exactament 4 elements per definir el for"))
        
        :else
        (do 
          `(loop [~simbol ~(first iterable) restant# ~iterable valor# nil]
             (if (not (empty? restant#))
               (let [
                     nou-valor# (do ~@cos)
                     nou-restant# (pop restant#)
                     nou-simbol# (first nou-restant#)
                     ]
                 (recur nou-simbol# nou-restant# nou-valor#)
                 ) 
               valor#)
             )
          ) 
        ) 
  )

(println "foreach")
(println (foreach [x [1 2 3]] (println (inc x))))

;; Composició de funcions
