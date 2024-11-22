(ns arbre_binari)

;; 0 Representació
;{:val n, :L <bst esq.>, :R <bst dre.>}
(def t2 {:val 2 :L {:val 4 :L nil :R nil} :R {:val 5 :L nil :R nil}})
(def t3 {:val 3 :L {:val 6 :L nil :R nil} :R {:val 7 :L nil :R nil}})
(def t1 {:val 1 :L t2 :R t3})
(def t13 {:val 1 :L t2 :R t3})

;;1
(defn size [a]
  (let [{:keys [val L R]} a]
    (if (nil? a) 0
        (+ 1 (size L) (size R)))
    )
  
  )

(println "size")
(println (size t1))
(println (size t2))
(println (size t3))
(println)

;;2
(defn height [a]
  (let [{:keys [val L R]} a]
    (if (nil? a) 0
        (inc (max (height L) (height R))))
    )
  )

(println "height")
(println (height t1))
(println (height t2))
(println (height t3))
(println)

;;3
(defn equal [a1 a2]
  (let [{val1 :val L1 :L1 R1 :R1} a1 {val2 :val L2 :L2 R2 :R2} a2] 
    (if (or (nil? val1) (nil? val2)) (= val1 val2) 
        (and (= val1 val2) (equal L1 L2) (equal R1 R2))
          )
    
    )
  )

(println "equal")
(println (equal t1 t13))
(println (equal t2 t3))
(println)

;;4
(defn pre-order [a]
  (let [{:keys [val L R]} a]
    (if (nil? a) '()
      (concat (list val) (pre-order L) (pre-order R))
      )
    )
  )

(println "pre-order")
(println (pre-order t1))
(println)

;;5
(defn post-order [a]
  (let [{:keys [val L R]} a]
    (if (nil? a) '()
        (concat (post-order L) (post-order R) (list val))))
  )
(println "post-order")
(println (post-order t1))
(println)

;;6
(defn in-order [a]
  (let [{:keys [val L R]} a]
    (if (nil? a) '()
        (concat (in-order L) (list val) (in-order R))))
  )

(println "in-order")
(println (in-order t1))
(println)

;;7
(defn breadth-first [tree]
  (let [enqueue #(conj %1 %2)    ;; Function to add an element to the queue
        dequeue #(subvec % 1)]   ;; Function to remove the first element of the queue
    (loop [queue [tree]          ;; Initialize the queue with the root node
           result []]            ;; Accumulator for result
      (if (empty? queue)         ;; Base case: stop if the queue is empty
        result                   ;; Return the accumulated result
        (let [{:keys [val L R]} (first queue) ;; Decompose the current node
              remaining (dequeue queue)]     ;; Get the rest of the queue
          (recur
           (cond-> remaining      ;; Update the queue
             L (enqueue L)        ;; Add left child if it exists
             R (enqueue R))       ;; Add right child if it exists
           (if val                ;; Add the current value to the result
             (conj result val)
             result))))))) 

(println "breadth-first")
(println (breadth-first t1))
(println)

;;8
(defn build [p i]
  (let [arrel (first p) 
        L (take-while #(not= arrel %) i)
        R (pop (drop-while #(not= arrel %) i))]
    {:val arrel :L (build Lp Li) :R (build Rp Rp)}
    )
  )

(def preO '(1 2 4 5 3 6 7))
(def inO '(4 2 5 1 6 3 7))
(println "build")
(println (post-order (build preO inO)))
(println)

