package com.nyc.schools.network

import com.nyc.schools.model.School
import com.nyc.schools.model.SchoolDetail
import retrofit2.http.GET

interface SchoolsAPI {
    @GET("s3k6-pzi2")
    suspend fun getSchools(): List<School>
    @GET("f9bf-2cp4")
    suspend fun getSchoolDetails(): List<SchoolDetail>
}