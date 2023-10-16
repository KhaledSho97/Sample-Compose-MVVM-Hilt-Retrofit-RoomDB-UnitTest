package com.khaled_sho.testmedicalapp.main.data.mapper

import com.khaled_sho.testmedicalapp.core.mappers.Mapper
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import javax.inject.Inject

class ListOfDrugMapper @Inject constructor() : Mapper<ProblemsResponse, List<AssociatedDrug>> {
    override fun toModel(value: ProblemsResponse): List<AssociatedDrug> {
        val listOfAssociatedDrug = arrayListOf<AssociatedDrug>()
        val classNames =
            value.problems!![0].diabetes[0].medications[0]
                .medicationsClasses[0]
                .values.toList()

        for (classObj in classNames) {
            for (cl in classObj[0]) {
                listOfAssociatedDrug.addAll(cl.value)
            }
        }
        return listOfAssociatedDrug
    }

    override fun fromModel(value: List<AssociatedDrug>): ProblemsResponse {
        TODO("Not yet implemented")//not needed
    }
}