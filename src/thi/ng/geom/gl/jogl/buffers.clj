(ns thi.ng.geom.gl.jogl.buffers
  (:import
   [com.jogamp.opengl.util GLBuffers]
   [java.nio Buffer DoubleBuffer FloatBuffer ShortBuffer IntBuffer]))

;; Native buffer wrappers for Clojure / JOGL
;; TODO refactor to avoid 2x copy via array, fill directly

(defn ^FloatBuffer float-buffer
  [n-or-coll]
  (if (number? n-or-coll)
    (FloatBuffer/allocate (int n-or-coll))
    (-> n-or-coll float-array FloatBuffer/wrap)))

(defn ^FloatBuffer float-buffer-direct
  [n-or-coll]
  (if (number? n-or-coll)
    (GLBuffers/newDirectFloatBuffer (int n-or-coll))
    (-> n-or-coll float-array GLBuffers/newDirectFloatBuffer)))

(defn ^DoubleBuffer double-buffer
  [n-or-coll]
  (if (number? n-or-coll)
    (DoubleBuffer/allocate (int n-or-coll))
    (-> n-or-coll double-array DoubleBuffer/wrap)))

(defn ^DoubleBuffer double-buffer-direct
  [n-or-coll]
  (if (number? n-or-coll)
    (GLBuffers/newDirectDoubleBuffer (int n-or-coll))
    (-> n-or-coll double-array GLBuffers/newDirectDoubleBuffer)))

(defn ^ShortBuffer short-buffer
  [n-or-coll]
  (if (number? n-or-coll)
    (ShortBuffer/allocate (int n-or-coll))
    (-> n-or-coll short-array ShortBuffer/wrap)))

(defn ^ShortBuffer short-buffer-direct
  [n-or-coll]
  (if (number? n-or-coll)
    (GLBuffers/newDirectShortBuffer (int n-or-coll))
    (-> n-or-coll short-array GLBuffers/newDirectShortBuffer)))

(defn ^IntBuffer int-buffer
  [n-or-coll]
  (if (number? n-or-coll)
    (IntBuffer/allocate (int n-or-coll))
    (-> n-or-coll int-array IntBuffer/wrap)))

(defn ^IntBuffer int-buffer-direct
  [n-or-coll]
  (if (number? n-or-coll)
    (GLBuffers/newDirectIntBuffer (int n-or-coll))
    (-> n-or-coll int-array GLBuffers/newDirectIntBuffer)))

(defn copy-float-buffer
  [^FloatBuffer dest ^FloatBuffer src did sid len]
  (.position dest (int did))
  (loop [len len, did did, sid sid]
    (when (pos? len)
      (.put dest (.get src (int sid)))
      (recur (dec len) (unchecked-inc did) (unchecked-inc sid)))))

(defn copy-float-buffer-vec2
  [^FloatBuffer dest ^FloatBuffer src did sid]
  (doto dest
    (.position (int did))
    (.put (.get src (int sid)))
    (.put (.get src (unchecked-add-int sid 1)))))

(defn copy-float-buffer-vec3
  [^FloatBuffer dest ^FloatBuffer src did sid]
  (doto dest
    (.position (int did))
    (.put (.get src (int sid)))
    (.put (.get src (unchecked-add-int sid 1)))
    (.put (.get src (unchecked-add-int sid 2)))))

(defn copy-float-buffer-vec4
  [^FloatBuffer dest ^FloatBuffer src did sid]
  (doto dest
    (.position (int did))
    (.put (.get src (int sid)))
    (.put (.get src (unchecked-add-int sid 1)))
    (.put (.get src (unchecked-add-int sid 2)))
    (.put (.get src (unchecked-add-int sid 3)))))
