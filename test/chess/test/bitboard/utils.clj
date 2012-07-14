(ns chess.test.bitboard.utils
  (:use [clojure.test])
  (:use [chess.bitboard.utils] :reload))

(deftest test-index->rank
  (is (= (index->rank 3) 1))
  (is (= (index->rank 32) 5))
  (is (= (index->rank 63) 8)))

(deftest test-index->file
  (is (= (index->file 63) 1))
  (is (= (index->file 45) 3))
  (is (= (index->file 4) 4)))

(deftest test-get-rank
  (is (= (get-rank 2638848 2) '(\0 \1 \0 \0 \0 \1 \0 \0)))
  (is (= (get-rank 0 1) '(\0 \0 \0 \0 \0 \0 \0 \0))))

(deftest test-get-file
  (is (= (get-file 2638848 3) '(\0 \0 \0 \0 \0 \1 \0 \0)))
  (is (= (get-file 2638848 2) '(\0 \0 \0 \0 \0 \0 \1 \0)))
  (is (= (get-file 0 1) '(\0 \0 \0 \0 \0 \0 \0 \0))))
