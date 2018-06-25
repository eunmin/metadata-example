(ns metadata-example.core)

;; var
(def ^{:foo 1} x 1)

(meta #'x)

(alter-meta! #'x #(update % :foo (constantly 2)))

(meta #'x)

;; symbol
(let [sym (with-meta 'a {:foo 1})]
  (meta sym))

(defn meta-pass-fn [sym]
  (meta sym))

(meta-pass-fn (with-meta 'y {:foo 1}))

(meta-pass-fn (with-meta [] {:foo 1}))

;; annotation

(defrecord ^{Deprecated true} AnnotationTest [])

(seq (.getAnnotations AnnotationTest))

(eval (list 'defrecord (with-meta 'AnnotationTest2 {'Deprecated true}) []))

(seq (.getAnnotations AnnotationTest2))

(defmacro record-form [class-name]
  (let [class-name-with-meta (with-meta class-name {'Deprecated true})]
    (list 'defrecord class-name-with-meta [])))

(record-form ^{Deprecated true} AnnotationTest3)

(seq (.getAnnotations AnnotationTest3))
