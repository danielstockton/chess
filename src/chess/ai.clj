(ns chess.ai)

; ---------------
; Tree Generation
; ---------------

(defn game-tree
  "Generate a tree of posible next boards starting with a given board position."
  [board make-move]
  {:node board
   :children (map #(game-tree % make-move)
                  (make-move board))})

; ---------------
; Tree Evaluation
; ---------------

(declare minimize)

(defn maximize [evaluator tree]
  (if (seq (:children tree))
    (apply max
           (map #(minimize evaluator %)
                (:children tree)))
    (evaluator (:node tree))))

(defn minimize [evaluator tree]
  (if (seq (:children tree))
    (apply min
           (map #(maximize evaluator %)
                (:children tree)))
    (evaluator (:node tree))))

(defn evaluator
  "Dynamic evaluation of a game tree. Returns a number representing how good
  the root position is. Uses minimax algorithm"
  [static-evaluator]
  (fn [tree]
    (minimize static-evaluator tree)))

(defn best-move
  "Get the best computer move for the given game tree.
  static-evaluator evaluates single positions, without looking at the tree, and
  returning a number"
  [tree static-evaluator]
  (:node (apply max-key
                (evaluator static-evaluator)
                (:children tree))))
