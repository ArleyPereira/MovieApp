package br.com.hellodev.movieapp.presenter.main.bottombar.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.BottomSheetLogoutBinding
import br.com.hellodev.movieapp.databinding.FragmentProfileBinding
import br.com.hellodev.movieapp.domain.model.menu.MenuProfile
import br.com.hellodev.movieapp.domain.model.menu.MenuProfileType
import br.com.hellodev.movieapp.presenter.auth.activity.AuthActivity
import br.com.hellodev.movieapp.presenter.auth.activity.AuthActivity.Companion.AUTHENTICATION_PARAMETER
import br.com.hellodev.movieapp.presenter.auth.enums.AuthenticationDestinations
import br.com.hellodev.movieapp.presenter.main.bottombar.profile.adapter.ProfileMenuAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: ProfileMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configData()

        initRecycler()
    }

    private fun initRecycler() {
        mAdapter = ProfileMenuAdapter(
            items = MenuProfile.items,
            context = requireContext(),
            onClick = { type ->
                when (type) {
                    MenuProfileType.PROFILE -> {
                        findNavController().navigate(R.id.action_menu_profile_to_editProfileFragment)
                    }

                    MenuProfileType.NOTIFICATION -> {

                    }

                    MenuProfileType.DOWNLOAD -> {
                        val bottomNavigation =
                            activity?.findViewById<BottomNavigationView>(R.id.btnv)
                        bottomNavigation?.selectedItemId = R.id.menu_download
                    }

                    MenuProfileType.SECURITY -> {

                    }

                    MenuProfileType.LANGUAGE -> {

                    }

                    MenuProfileType.DARK_MODE -> {

                    }

                    MenuProfileType.HELPER -> {

                    }

                    MenuProfileType.PRIVACY_POLICY -> {

                    }

                    MenuProfileType.LOGOUT -> {
                        showBottomSheetLogout()
                    }
                }
            }
        )

        with(binding.recyclerItems) {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun configData() {
        binding.imageProfile.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.person_placeholder
            )
        )

        binding.textUsername.text = "Arley Santana"
        binding.textEmail.text = "andrew_ainsley@yourdomain.com"
    }

    private fun showBottomSheetLogout() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val bottomSheetBinding = BottomSheetLogoutBinding.inflate(
            layoutInflater, null, false
        )

        bottomSheetBinding.btnCancel.setOnClickListener { bottomSheetDialog.dismiss() }
        bottomSheetBinding.btnConfirm.setOnClickListener {
            bottomSheetDialog.dismiss()
            logout()
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        activity?.finish()
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.putExtra(AUTHENTICATION_PARAMETER, AuthenticationDestinations.LOGIN_SCREEN)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}