'use strict';

jQuery(($) => {

    // =========================
    // イベント登録
    // =========================
    $('#btn-signup').on('click', (e) => {
        e.preventDefault();
        signupUser();
    });

});


/**
 * ユーザー登録処理
 */
function signupUser() {

    clearValidation();

    const formData = $('#signup-form').serialize();

    $.ajax({
        type: 'POST',
        url: '/user/signup/rest',
        data: formData,
        dataType: 'json',
        cache: false
    })
    .done((data) => {

        if (data.result === 90) {
            // バリデーションエラー
            Object.entries(data.errors).forEach(([key, value]) => {
                showValidationError(key, value);
            });

        } else if (data.result === 0) {
            alert('ユーザーを登録しました');
            window.location.href = '/login';
        }

    })
    .fail((jqXHR) => {
        console.error('Signup Error:', jqXHR.status, jqXHR.responseText);
        alert('ユーザー登録に失敗しました');
    });
}


/**
 * バリデーション結果をクリア
 */
function clearValidation() {
    $('.is-invalid').removeClass('is-invalid');
    $('.invalid-feedback').remove();
    $('.text-danger').remove();
}


/**
 * バリデーション結果表示
 */
function showValidationError(key, message) {

    const $targetById = $('#' + key);
    const $targetByName = $('[name="' + key + '"]');

    // 性別（radioボタン等）対応
    if ($targetByName.length > 1) {
        $targetByName.addClass('is-invalid');
        $targetByName.last().closest('.form-group')
            .append(`<div class="text-danger">${message}</div>`);
        return;
    }

    // 通常input
    if ($targetById.length) {
        $targetById.addClass('is-invalid');
        $targetById.after(`<div class="invalid-feedback">${message}</div>`);
    }
}