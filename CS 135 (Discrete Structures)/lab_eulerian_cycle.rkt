#lang eopl
;; An undirected graph is given as a relation that is symmetric but
;; every (a b) and (b a) pair should be treated as equivalent
;; vertices will be labelled starting at one and incrementing by one each time.
;; Below are some undirected graphs
(define cycle6 '((1 2) (2 3) (3 4) (4 5) (5 6) (6 1) (2 1) (3 2) (4 3) (5 4) (6 5) (1 6)))
(define david '((3 1) (1 3) (4 2) (2 4) (5 3) (3 5) (6 4) (4 6) (1 5) (5 1) (2 6) (6 2)))
(define study '((1 6) (2 3) (3 1) (4 1) (5 1) (6 1) (2 2) (3 2) (1 3) (1 4) (1 5) (6 5) (5 6) (2 4) (4 2)))
(define test '((2 3) (4 5) (5 2) (2 1) (3 2) (1 2) (3 3) (5 4) (2 5) (1 1)))



;; Define degree
;; degree takes an undirected graph and a given vertex
;; this will return the number of edges connected to the vertex
;; to do this check how many edges start at the same vertex as the given one
;; A reflexive edge is special in that it counts as 2 edges but is only written once

;; Examples:
;; (degree cycle6 4) -> 2
;; (degree david 2) -> 2
;; (degree study 1) -> 4
;; (degree test 1) -> 3

;; Type Signature: (degree relation int) -> int

(define (degree relation vertex)
  (if (null? relation)
      0
      (if (contains (car relation) vertex)
          (if (equal? (car (car relation)) (car (cdr (car relation))))
              (+ 2 (degree (cdr relation) vertex))
              (+ 1 (degree (cdr relation) vertex)))
          (+ 0 (degree (cdr relation) vertex)))))

(define (contains edge vertex)
  (if (equal? (car edge) vertex)
      #t
      #f))

;; Define maxVertex
;; maxVertex takes an undirected graph
;; It should return the max vertex (greatest number)
;; Do this by recursively going through relation and storing the current max each time
;; a helper may be useful

;; Examples:
;; (maxVertex cycle6) -> 6
;; (maxVertex '()) -> 1
;; (maxVertex '((1 1) (3 7) (7 3) (2 4) (4 2))) -> 7

;; Type Signature: (maxVertex relation) -> int

(define (maxVertex relation)
   (if (null? relation)
       1
       (helperMax relation 1)))

(define (helperMax relationIteration lastMax)
  (if (null? relationIteration)
      lastMax
      (if (or (> (car (cdr (car relationIteration))) lastMax) (> (car (car relationIteration)) lastMax))
          (if (> (car (cdr (car relationIteration))) lastMax)
              (helperMax (cdr relationIteration) (car (cdr (car relationIteration))))
              (helperMax (cdr relationIteration) (car (car relationIteration))))
          (helperMax (cdr relationIteration) lastMax))))


;; Define has-eulerian-cycle?
;; This is given an undirected graph and should return if the graph contains a eulerian cycle
;; A eulerian cycle exists iff every vertex has an even degree
;; a helper will be needed to keep track of what vertex you are checking

;; Examples:

;; Type Signature: (has-eulerian-cycle? relation) -> boolean
;; (has-eulerian-cycle? cycle6) -> #t
;; (has-eulerian-cycle? david) -> #t
;; (has-eulerian-cycle? study) -> #t
;; (has-eulerian-cycle? test) -> #f

(define (has-eulerian-cycle? relation)
   (if (null? relation)
       #f
       (eulerianHelper relation relation)))

(define (eulerianHelper relation relationIteration)
  (if (null? relationIteration)
      #t
      (if (and (even? (degree relation (car (cdr (car relationIteration))))) (even? (degree relation (car (car relationIteration)))))
          (eulerianHelper relation (cdr relationIteration))
          #f)))

