;;; Useful constants and data

(ns chess.data)

(def initial-board [\r \n \b \q \k \b \n \r
                    \p \p \p \p \p \p \p \p
                    \- \- \- \- \- \- \- \-
                    \- \- \- \- \- \- \- \-
                    \- \- \- \- \- \- \- \-
                    \- \- \- \- \- \- \- \-
                    \P \P \P \P \P \P \P \P
                    \R \N \B \Q \K \B \N \R])

(defn material-value [piece] ({\p 1 \n 3 \b 3 \r 5 \q 9} (Character/toLowerCase piece)))

(def pawn-scores [0   0   0   0   0   0   0   0
                  5   5   5   0   0   5   5   5
                  0   0   5  15  15   5   0   0
                  5   5  10  15  15  10   5   5
                  0   0   5  10  10   5   0   0
                  0   0   0   0   0   0   0   0
                  0   0   0   0   0   0   0   0
                  0   0   0   0   0   0   0   0])

(def knight-scores [-10 -5 -5  -5  -5  -5  -5 -10
                    -5  -5  0   5   5   0  -5 -5
                    -5   5  5   10  10  5   5 -5
                    -5   0  10  10  10  10  0 -5
                    -5   0  10  10  10  10  0 -5
                    -5   5  5   10  10  10  5 -5
                    -5  -5  0   5   5   0  -5 -5
                    -10 -5  -5  -5  -5  -5 -5 -10])

(def bishop-scores [-15 -10 -10 -10 -10 -10 -10 -15
                    -10   0   0   0   0   0   0 -10
                    -10   0   5  10  10   5   0 -10
                    -10   5   5  10  10   5   5 -10
                    -10   0  10  10  10  10   0 -10
                    -10  10  10  10  10  10  10 -10
                    -10   5   0   0   0   0   5 -10
                    -15 -10 -10 -10 -10 -10 -10 -15])

(def king-scores [  0 -5    5   0   0  -5   5   0
                  -10 -10 -10 -10 -10 -10 -10 -10
                  -10 -10 -10 -10 -10 -10 -10 -10
                  -10 -10 -10 -10 -10 -10 -10 -10
                  -10 -10 -10 -10 -10 -10 -10 -10
                  -10 -10 -10 -10 -10 -10 -10 -10
                  -10 -10 -10 -10 -10 -10 -10 -10
                  -10 -10 -10 -10 -10 -10 -10 -10])

(def initial-fen "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
