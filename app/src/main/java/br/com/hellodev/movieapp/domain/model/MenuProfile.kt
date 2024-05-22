package br.com.hellodev.movieapp.domain.model

import br.com.hellodev.movieapp.R

data class MenuProfile(
    val text: Int,
    val icon: Int,
    val type: MenuProfileType
) {
    companion object {
        val items = listOf(
            MenuProfile(
                text = R.string.text_edit_profile_profile_fragment,
                icon = R.drawable.ic_profile,
                type = MenuProfileType.PROFILE
            ),
            MenuProfile(
                text = R.string.text_notificate_profile_fragment,
                icon = R.drawable.ic_notification,
                type = MenuProfileType.NOTIFICATION
            ),
            MenuProfile(
                text = R.string.text_download_profile_fragment,
                icon = R.drawable.ic_download_profile,
                type = MenuProfileType.DOWNLOAD
            ),
            MenuProfile(
                text = R.string.text_security_profile_fragment,
                icon = R.drawable.ic_security,
                type = MenuProfileType.SECURITY
            ),
            MenuProfile(
                text = R.string.text_language_profile_fragment,
                icon = R.drawable.ic_language,
                type = MenuProfileType.LANGUAGE
            ),
            MenuProfile(
                text = R.string.text_dark_mode_profile_fragment,
                icon = R.drawable.ic_dark_mode,
                type = MenuProfileType.DARK_MODE
            ),
            MenuProfile(
                text = R.string.text_helper_profile_fragment,
                icon = R.drawable.ic_info,
                type = MenuProfileType.HELPER
            ),
            MenuProfile(
                text = R.string.text_privacy_policy_profile_fragment,
                icon = R.drawable.ic_privacy_policy,
                type = MenuProfileType.PRIVACY_POLICY
            ),
            MenuProfile(
                text = R.string.text_logout_profile_fragment,
                icon = R.drawable.ic_logout,
                type = MenuProfileType.LOGOUT
            )
        )
    }
}

enum class MenuProfileType {
    PROFILE,
    NOTIFICATION,
    DOWNLOAD,
    SECURITY,
    LANGUAGE,
    DARK_MODE,
    HELPER,
    PRIVACY_POLICY,
    LOGOUT
}