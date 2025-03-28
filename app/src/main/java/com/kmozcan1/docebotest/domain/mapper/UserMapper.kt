package com.kmozcan1.docebotest.domain.mapper

import com.kmozcan1.docebotest.data.apimodel.GetUserResponse
import com.kmozcan1.docebotest.domain.model.UserProfileModel
import javax.inject.Inject

/**
 * Created by Kadir Mert Özcan on 03-Jan-21.
 *
 * Maps [GetUserResponse] response object to [UserProfileModel] domain model
 */
class UserMapper @Inject constructor(): Mapper<GetUserResponse, UserProfileModel> {
    override fun map(repositoryModel: GetUserResponse) =
        repositoryModel.run {
            UserProfileModel(
                userName = login,
                fullName = if (name == null) login else name!!,
                email = (if (email == null) "" else email)!!,
                profileUrl = htmlUrl,
                avatarUrl = avatarUrl,
                location = (if (location == null) "" else location)!!
            )
        }
}