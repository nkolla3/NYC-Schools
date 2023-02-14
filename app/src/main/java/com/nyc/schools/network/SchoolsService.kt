package com.nyc.schools.network

import com.nyc.schools.model.School
import com.nyc.schools.model.SchoolDetail

class SchoolsService {
    private var schoolsAPI: SchoolsAPI = Retrofit.instance.create(SchoolsAPI::class.java)

    suspend fun getSchools(): List<School> = schoolsAPI.getSchools()
    suspend fun getSchoolDetails(): List<SchoolDetail> = schoolsAPI.getSchoolDetails()
}