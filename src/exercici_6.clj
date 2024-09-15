(ns exercici_6)

;; apartat 1
(defn my-count1 
    ([lst] (my-count1 lst 0))
    ([lst n] 
        (if (empty? lst)
            n
            (let [k (inc n)]
                (recur (pop lst) k)
            )
        )
    )
)

(println "my-count1 0-elem:" (my-count1 '()))
(println "my-count1 3-elem:" (my-count1 '(1 2 2)))

(println)

;; apartat 2
(defn my-count2 [lst]
    (loop [lst2 lst n 0]
        (if (empty? lst2)
            n
            (let [k (inc n)]
                (recur (pop lst2) k)
            )
        )
    )
)

(println "my-count2 0-elem:" (my-count2 '()))
(println "my-count2 3-elem:" (my-count2 '(1 2 2)))

(println)

;; apartat 3
(defn my-maximum1 
    ([lst] 
        (cond (empty? lst) "empty list has no max"
              (= 1 (count lst)) (first lst)  ;; aquest cas no cal
              :else (my-maximum1 (pop lst) (first lst))
        )
    )
    ([lst cm]
        (if (empty? lst)
            cm
            (let [ncm (first lst) new_lst (pop lst)]
                (if (> ncm cm)
                    
                    (recur new_lst ncm)
                    
                    (recur new_lst cm)
                )
            )
        )
    )
)

(println "my-maximum1 value5:" (my-maximum1 '(4 3 1 5 4 5 2)))
(println "my-maximum1 value999:" (my-maximum1 '(41 414 -5100 999 0 998 0)))
(println "my-maximum1 empty list:" (my-maximum1 '()))
(println "my-maximum1 value3:" (my-maximum1 '(3)))

(println)

;; apartat 4
(defn my-maximum2 [lst]
    (if (empty? lst) 
        "empty list has no max"
        (loop [lst2 lst cm (first lst)]
            (if (empty? lst2)
                cm
                (let [ncm (first lst2) new_lst (pop lst2)]
                    (if (> ncm cm)
                        
                        (recur new_lst ncm)
                        
                        (recur new_lst cm)
                    )
                )
            ) 
        )
    )
)

(println "my-maximum2 value5:" (my-maximum2 '(4 3 1 5 4 5 2)))
(println "my-maximum2 value999:" (my-maximum2 '(41 414 -5100 999 0 998 0)))
(println "my-maximum2 empty list:" (my-maximum2 '()))
(println "my-maximum2 value3:" (my-maximum2 '(3)))

(println)

;; apartat 5
(defn average1 
    ([lst]
        (if (empty? lst) 
            "empty list has no mean"
            (average1 (pop lst) (first lst) (count lst))
        )
    ) 
    ([lst suma n-elem]
        (if (empty? lst)
            (/ suma n-elem)
            (let [new_suma (+ suma (first lst)) new_lst (pop lst)]
                (recur new_lst new_suma n-elem)
            )
        )

    )
)

(println "average1 2:" (average1 '(1 2 3)))
(println "average1 10:" (average1 '(40 0 -10)))
(println "average1 11:" (average1 '(11)))
(println "average1 12:" (average1 '(11 13)))
(println "average1 0-elems:" (average1 '()))

(println)

;; apartat 6
(defn average2 [lst]
    (if (empty? lst) 
        "empty list has no mean"
        (let [n-elem (count lst)]
            (loop [lst2 lst suma 0]
                (if (empty? lst2)
                    (/ suma n-elem)
                        (let [new_suma (+ suma (first lst2)) new_lst (pop lst2)]
                            (recur new_lst new_suma)
                        )
                )
            )
        )
    )
)

(println "average2 2:" (average2 '(1 2 3)))
(println "average2 10:" (average2 '(40 0 -10)))
(println "average2 11:" (average2 '(11)))
(println "average2 12:" (average2 '(11 13)))
(println "average2 0-elems:" (average2 '()))