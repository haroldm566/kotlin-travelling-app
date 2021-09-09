package op.mobile.app.dev.mandha1.travelling.login

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.mandha1.travelling.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.mandha1.travelling.model.Login

class LoginViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(login: Login) {
        binding.login = login
        binding.executePendingBindings()
    }
}