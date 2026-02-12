package com.example;

/**
 * Маркер + источник "человеческого" имени типа для логов/ошибок.
 * Можно оставить дефолтный метод — тогда ничего не надо переопределять.
 */
public interface ValidatableMessage {

    /**
     * Как назвать объект в логах/ошибках.
     * По умолчанию = SimpleName класса DTO.
     */
    default String objectType() {
        return this.getClass().getSimpleName();
    }
}