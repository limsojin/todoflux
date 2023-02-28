package com.example.demo.custom;

public enum ErrorCode
{
    /**
     * 성공 / 실패 여부
     */
    TODO_SUCCESS(true, "200", "todolist 등록 성공"), TODO_ERROR(false, "400", "todolist 등록 실패"), UPDATE_SUCCESS(true, "201",
                    " todolist 업데이트 완료"), UPDATE_ERROR(false, "401", "todolist 업데이트 실패 key를 확인하세요"), DELETE_SUCCESS(true,
                                    "204", "todolist 삭제 성공"), DELETE_ERROR(false, "404", "todolist 삭제 실패");

    private final boolean isSuccess;
    private final String isResultCd;
    private final String getMessage;

    ErrorCode ( boolean isSuccess,
                String isResultCd,
                String getMessage )
    {
        this.isSuccess = isSuccess;
        this.isResultCd = isResultCd;
        this.getMessage = getMessage;
    }

    public boolean isSuccess ()
    {
        return this.isSuccess;
    }

    public String getMessage ()
    {
        return this.getMessage;
    }

    public String isResultCd ()
    {
        return this.isResultCd;
    }
}
