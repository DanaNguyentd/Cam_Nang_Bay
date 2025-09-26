package com.example.hotrobay.userdata

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<User> {
    override val defaultValue: User = User.newBuilder()
        .setFirstName("Van A")
        .setLastName("Nguyen")
        .setBirthday("02-07-1986")
        .setNationality("Việt Nam")
        .setGender("Male")
        .setPassportId("Cxxxxxx")
        .setPassportExpiryDate("12-02-2067")
        .setAddress("Số 4, ngách 6, ngõ 7,\nduong a\nPhường b, Hà Nội")
        .setMedicalHistory(
            MedicalHistory.newBuilder()
                .addMedications("Blood pressure medication/Thuốc Cao Huyết Áp")
                .addDiseases("Blood pressure/Cao Huyết Áp")
                .setBloodType("O(+)")
                .build()
        )
        .addContactNumbers("Con trai ruột/My son--B Nguyen--+84 xxx.xxx.xxx")
        .addContactNumbers("Con dâu/My daughter in law--C Tran--+84 xxx.xxx.xxx")
        .build()

    override suspend fun readFrom(input: InputStream): User {
        try {
            return User.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }
}
