#lang eopl
;;Remember that empty list is valid input unless otherwise stated


;;Define composite
;; Composite takes an inner relation and an outer relation and returns a relation
;; For every (a b) tuple in the inner relation if b equals an A in a (A B) tuple in the outer relation
;; then add the duple (a B) to the relation you will be returning
;; order doesn't matter

;; Examples:
;; (composite '((2 4) (3 5) (4 6) (5 7)) '((1 2) (2 3) (3 4) (4 5)) ) -> '((1 4) (2 5) (3 6) (4 7))
;; (composite '((2 4) (5 8) (3 4)) '((2 4) (5 8) (3 4))) -> '()
;; (composite '((1 1) (2 2) (3 3)) '((1 1) (2 2) (3 3))) -> '((1 1) (2 2) (3 3))

;; Type Signature: (composite relation relation) -> relation

(define (composite  relationOuter relationInner)
  (if (null? relationOuter)
      '()
      (if (null? relationInner)
          '()
          (if (equal? (car (reverse (car relationInner))) (car (car relationOuter)))
              (cons (cons (car (car relationInner)) (cdr (car relationOuter))) (composite relationOuter (cdr relationInner)))
              (if (null? relationOuter)
                  (composite relationOuter (cdr relationInner))
                  (composite (cdr relationOuter) relationInner))))))

;; Define power
;; power takes a relation and applies composite to itself k times
;; You will need to use a helper to store the initial relation
;; when k is zero it should return '()
;; order doesn't matter

;; Examples:
;; (power '((1 2) (2 3) (3 4) (4 1)) 0) -> '()
;; (power '((1 2) (2 3) (3 4) (4 1)) 1) -> '((1 2) (2 3) (3 4) (4 1))
;; (power '((1 2) (2 3) (3 4) (4 1)) 2) -> '((1 3) (2 4) (3 1) (4 2))
;; (power '((1 2) (2 3) (3 4) (4 1)) 3) -> '((1 4) (2 1) (3 2) (4 3))

;; Type Signature: (power relation int) -> relation

(define (power relation k)
  (if (equal? k 0)
      '()
      (myPOWERfulLittleHelper relation relation k)))



(define (myPOWERfulLittleHelper relation relationHelper k)
  (if (equal? k 1)
      relationHelper
      (myPOWERfulLittleHelper relation (composite relation relationHelper) (- k 1))))

;; Define transitive-closure
;; transitive-closure should compute the transitive-closure of a relation
;; Transitive-closure is the union of all possible powers of the relation
;; a helper will be needed to keep track of the current power
;; each (a b) tuple can only be used at most once in a path in power 
;; order doesn't matter

;; Examples:
;; (transitive-closure '((1 2) (2 3) (3 1))) -> '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3))
;; (transitive-closure '((1 3) (3 5) (2 4) (5 6) (2 3))) -> '((1 3) (3 5) (2 4) (5 6) (2 3) (1 5) (1 6) (3 6) (2 5) (2 6))

;; Type Signature: (transitive-closure relation) -> relation

(define (transitive-closure relation)
  (helpMeCloseThisTransitively relation relation (length relation)))

(define (helpMeCloseThisTransitively r rel len)
  (if (equal? len 0)
      '()
      (if (equal? len 1)
          rel
          (union rel (helpMeCloseThisTransitively r (power r len) (- len 1))))))

;; Define transitive?
;; returns if a given relation is transitive

;; Examples:
;; (transitive? '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3))) -> #t
;; (transitive? '((1 2) (2 3) (3 1))) -> #f

;; Type Signature: (transitive? relation) -> boolean

(define (transitive? relation)
   (subset? (transitive-closure relation) relation))

;; Define EQ-relation?
;; returns if a given relation is an EQ-relation
;; A relation is an EQ-relation if it is symmetric, reflexive, and transitive

;; Examples:
;; (EQ-relation? '((1 1) (1 2) (1 3) (2 1) (2 2) (2 3) (3 1) (3 2) (3 3)) 3) -> #t
;; (EQ-relation? '((1 1) (1 2) (2 1)) 2) -> #f
(define (EQ-relation? relation n)
  (if (reflexive? relation n)
      (if (symmetric? relation)
          (transitive? relation)
          #f)
      #f))


;;_________________________________________________________________
(define (element? item list-of-items)
  (if (null? list-of-items)                  ;Is our "set" empty?
      #f                                     ;If empty, not an element!
      (if (equal? item (car list-of-items))  ;Is our item first in list?
          #t                                 ;Yes?  Then it's an element!
          (element? item (cdr list-of-items)))));No? Check the rest.

(define (make-set list-of-items)
  (if (null? list-of-items) ;An empty list can have no duplicates,
      '()                   ;so just return an empty list.
      (if (element? (car list-of-items) (cdr list-of-items))
          (make-set (cdr list-of-items))
          (cons (car list-of-items) (make-set (cdr list-of-items))))))
         
(define (union setA setB)
  (make-set (append setA setB))) 

(define (intersection setA setB)
  (make-set (Intersection (make-set setA) (make-set setB))))

(define (Intersection setA setB)
  (if (null? setA) 
      '()
      (if (element? (car setA) setB)
          (cons (car setA) (intersection (cdr setA) setB))
          (intersection (cdr setA) setB))))

(define (subset? setA setB)
  (if (null? setA)
      #t
      (if (element? (car setA) setB)
          (subset? (cdr setA)  setB)
          #f)))

(define (set-equal? setA setB)
   (and (subset? setA setB) (subset? setB setA)))

(define (proper-subset? setA setB)
  (and (subset? setA setB) (not (set-equal? setA setB))))

(define (set-difference setA setB)
  (make-set (Set-Difference setA setB)))

(define (Set-Difference setA setB)
  (if (null? setA)
      '()
      (if (element? (car setA) setB)
          (Set-Difference (cdr setA) setB)
          (cons (car setA) (Set-Difference (cdr setA) setB)))))

(define (sym-diff setA setB)
  (union (set-difference setA setB) (set-difference setB setA)))

(define (cardinality set)
  (length (make-set set)))

(define (disjoint? setA setB)
  (null? (intersection setA setB)))

(define (superset? setA setB)
  (subset? setB setA))

(define (insert element set)
  (make-set (cons element set)))

(define (remove element set)
  (set-difference set (list element)))

(define (id n)
    (if (zero? n)
        '()
        (cons (list n n) (id (- n 1)))))

(define (reflexive? relation n)  
    (subset? (id n) relation))

(define (reflexive-closure relation n)
    (union relation (id n)))

(define (flip-pairs relation)
    (if (null? relation)
        '()
        (cons (reverse (car relation)) (flip-pairs (cdr relation)))))

(define (symmetric? relation)
   (set-equal? relation (flip-pairs relation)))

(define (symmetric-closure relation) 
   (union relation (flip-pairs relation)))

(define (related-to element relation)
   (if (null? relation)
      '()
      (if (equal? element (caar relation))
          (cons (cadar relation) (related-to element (cdr relation)))
          (related-to element (cdr relation)))))
